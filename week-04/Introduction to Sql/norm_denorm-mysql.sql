-- ********************************************************* START ****************************************************************
/*
Not all database schemas are equally effective. When we track data with significant conceptual scope, there's a lot that can go wrong. Problems include:
too much data in a single table
too many concepts in a single table
bad data type choices
unclear relationships
poor naming

What's worse is that the most academically "correct" schema uses many tables with many relationships. 
This requires complicated joins to return useful information, which reduces database performance. 
Sometimes the most correct schema is the schema that performs well and meets service level agreements, not the schema with the most beautiful data model.

Schema design is a design activity, so there's no perfect solution. 
Design is an exercise in trade-offs and compromise.

Normalization is the process of applying well-understood design rules to a schema. 
Early database researchers identified several normal forms that represent what they consider to be good schema design. 
Informally, normal form rules include:

If a table tracks multiple "things" (primary phone, secondary phone, work phone) in one row, move those things to a second table with a one-to-many relationship.
Don't track more than one concept in a single table. Move independent concepts to their own table.
Ensure that each concept is pure. Look for concept confusion. If there's confusion, restructure tables to clarify.
This is just a sample. There are at least eight named normal forms. We don't worry about the formal details. Instead, we rely on intuition.
*/
-- *************************************************** Competitive Robots *********************************************************
/*
We start with a one table schema and work our way through the normalization process.

Our database tracks the results of robot competitions. 
Competitors enter their robots in a competition. 
The robots solve a series of timed challenges. 
Robots are ranked from first place to last based on their cumulative results. 
Competitions are named, they occur once a year, and they're hosted by different cities each year.
*/
-- ******************************************* Extract One-to-Many Relationships **************************************************
/*
The first guidance provided by normalization rules is to extract one-to-many relationships posing as table attributes. 
In the competition_results table, the columns first_place_bot, second_place_bot, and third_place_bot seem to express facts about a competition,
but they also stand out as an independent concept that does not have a one-to-one relationship with a competition. 
Competition/rankings are one-to-many. The concept of a ranked competitor may warrant its own table.

The hidden one-to-many relationship in competition_results includes *_bot and *_competitor columns. 
Each bot/competitor combination represents a ranked placement. 
If we move these columns to an independent table, we gain new flexibility. 
We can rank any number of bots. 
If the second place bot is disqualified, it's easy to determine the new second and third place.

To link a bot's rank with a competition, we need the competition's key. 
Currently, the key is a combination of the year and competition columns.
That's enough to uniquely identify a row.

A key that uses meaningful attributes from a table is called a natural key. 
Year/competition is a natural key. Natural keys are nice because their values have meaning. 
We understand years and competition names. A database-generated key isn't meaningful. 
It's an arbitrary value. Some database designers prefer natural keys.

Natural keys have drawbacks. 
If Battle Jam decides to run two competitions in one year, the year/competition key is no longer unique.
Likewise, a second organization might start a competition called "Robot Olympics". 
That also violates key uniqueness. 
To solve the problem, we would need to add a third column to the natural key like month or organization. 
The world is a complicated and ever-changing place. 
If we don't know for certain that a natural key works 100% of the time, our natural key will also become complicated and ever-changing.

Natural keys are a little fussy. 
All key values must be embedded in every foreign key table. 
In the case of a competition and competition_rank, each competition_rank row would need a year and competition name. 
That's a lot of extra data.

Finally, if a natural key value can change, it's not a very good key. 
The folks at Battle Jam might worry about the violent undertones of the word "battle" and change their name to "Co-op Jam (where everyone's a winner!)". 
It's not an easy task to change that value throughout the database when it's part of a key.

For those reasons, some database designers prefer surrogate keys. 
A surrogate key is a generated value that's guaranteed to be unique and doesn't convey real-world meaning. 
Conceptually, it's a label, handle, or location that only has meaning in the database. 
It's used to quickly find a row.
*/
-- ************************************** Extract String Values that Repeat in a Column *******************************************
/*
Another normalization "tell" is repeated words in a column.
The competition table has repeated words in the name and host_city columns.
In name, each competition name is repeated three times.
In host_city, "Seoul" and "Chennai" appear twice.

The reason we worry about strings and not other data types is that strings often label an independent concept.
That's exactly what's happening in the competition table.
The concept of a city is entirely independent of the concept of a competition.
It likely deserves its own table.

The competition's name is also a candidate for an independent concept.
When we think of "Robot Cup", we think of a series of organized events.
Robot Cup has leadership and direction. 
It has a life of its own. 
It's not an attribute of a competition.
*/
-- *********************************************** Resolve Tangled Concepts *******************************************************
/*
The competition_rank table has the repeated string tell.
Both the bot and competitor columns have repeated string values.
A robot and a competitor are independent concepts apart from ranked positions in a competition. 
But before we break them into their own tables, let's consider relationships.

Competitors and their robots are not independent. 
A bot is always attached to a competitor.
The competitor competes through their bot.
When we examine the data closely, we also notice that one competitor can run multiple bots. 
Meggi Pien (Nek-Nek and Rip) and Isis Lafoy (D3tr0yer and Nales) each run two bots. 
In fact, Meggi Pien wins first and third place in the 2020 Battle Jam with different bots.

In other words, the relationship between competitor and bot is one-to-many and the relationship between a competition event and bot is many-to-many 
(events rank many bots and bots can participate in many events). 
It's the bot that's ranked, not the competitor, at least not directly. 
The competitor is only ranked through their relationship with a bot.

In table representation, a competitor is an independent concept. 
Bot is connected to competitor through a foreign key. 
We change competition_rank to competition_event_result to synchronize with the latest schema changes and create a foreign key relationship to bot.
*/
-- ***************************************************** Final Result *************************************************************
/*
Our initial schema contained a single table. It represented the data we needed but was hard to maintain.

After normalization, we have six tables. 
Many operations are safer and easier. 
The schema is open for expansion and elaboration.

The query that best estimates our original dataset follows. 
(It's possible to replicate the dataset exactly with a more complicated query. Here, we opt for relative simplicity.)
*/

select
    ce.year,
    c.name competition,
    city.name host_city,
    b.name bot,
    concat(cp.first_name, ' ', cp.last_name) competitor,
    r.rank
from competition_event ce
inner join competition c on ce.competition_id = c.competition_id
inner join city on ce.host_city_id = city.city_id
inner join competition_event_result r
    on ce.competition_event_id = r.competition_event_id
inner join bot b on r.bot_id = b.bot_id
inner join competitor cp on b.competitor_id = cp.competitor_id;
-- That's a lot of joins for what appears to be a simple request. Below is much easier.
select * from competition_results;

/*
Normalization and denormalization are design activities. 
That means there are no simple answers. 
To design is to choose one solution from many that have both pros and cons, benefits and costs.

The best way to learn design is to practice. 
Try many approaches in a low-risk environment and decide what works best. 
In the examples above, schemas from a single table to many could work. 
Each variation hurts (and feels natural) in different ways. 
If you have a hunch, try it and really analyze the outcome. 
What worked well? What didn't?
*/
-- ****************************************************** Conclusion **************************************************************
/*
1. Think of a one-to-many relationship. Sketch a database design that models the many as columns in a table. Then sketch another design that uses two related tables.
	- 
2. Why do string values that are repeated in a column cause concern? Do they always? Can you think of a scenario where they don't?
	- 
3. How does the DRY principle relate to normalization?
	- 
4. There's a subtle claim that joins are slow. Is that true?
	- 
*/
-- ********************************************************** END *****************************************************************
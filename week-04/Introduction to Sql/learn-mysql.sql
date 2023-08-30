-- This is how you write single line comments
select * from sys.sys_config;

-- MySQL supports three data type categories that are required by the ISO standard.
	-- Numbers: Whole or fractional numbers. Includes floating point representations (e.g. double) and precise decimals (e.g. decimal(12,4)).
		-- Integers: Integers are whole numbers. MySQL has five integer data types. They differ in size and the value range they can represent.
        -- Floating Point: Floating-point numbers represent very large and very small values, but can't represent those values precisely. 
		--                 Their main advantage is speed and storage efficiency. They are not safe to use for financial math and mission-critical calculations.
		-- Fixed Point: Fixed point numbers are declared with precision and scale arguments. The declaration below, decimal(8,2), can store 
        --              values between 999999.99 and -999999.99. That's 8 total digits with 2 digits after the decimal point.
    -- Strings: Represents text with various character encodings.
		-- Char: The char type is declared with a length argument. The declaration below, char(10), can store a maximum of 10 bytes worth of characters.
        -- Varchar: The varchar type is declared with a length argument. The declaration below, varchar(10), can store a maximum of 10 bytes worth of characters.
		-- Text: Text data types manage very large strings. They include tinytext, text, mediumtext, and longtext. longtext can store up to 4GB of text.
    -- Temporal Types: Date and time values.
		-- Date: date stores year, month, and day.
        -- Datetime: datetime stores year, month, day, hour, minute, second, and fractions of a second. Values range from 1000-01-01 00:00:00.000000 to 
        --           9999-12-31 23:59:59.999999. The larger range compared to timestamp requires more storage.
        -- Timestamp: timestamp stores year, month, day, hour, minute, second, and fractions of a second. Values range from 1970-01-01 00:00:01.000000 to 
        --            2038-01-19 03:14:07.999999. The smaller range compared to datetime requires less storage.
-- MySQL also supports extended types.
	-- Spatial: Geospatial values that make it easy to calculate distance on a globe.
	-- JSON: JSON is a data serialization format. In MySQL, it's compressed and indexed so it's possible to query a JSON object.
    
-- Choosing Between char and varchar
	-- varchar is almost always the right choice for string data. It's flexible, doesn't allocate storage needlessly, and can hold more data.
    -- The only exception is when a string column is used as a key or index. For example, a GUID (globally unique identifier) is fixed length text that is 
	-- used as a key. A fixed width index is slightly faster than a variable width index because the algorithm doesn't need to worry about length. 
    -- A fixed width value is always the same length. Variable width values are not.
	-- This only matters when data is very, very large. We favor varchar over char. When our row counts grow into the millions, we can worry about character 
    -- index performance.
    
-- Practice: Scan through the SQL below. Pick out the data types and list them.
	-- Also notice how they are being declared in MySQL!
create table listing (
    listing_id int primary key,
    name varchar(256) not null,
    host_id int not null,
    host_name varchar(50) not null,
    neighbourhood_group varchar(50) not null,
    neighbourhood varchar(50) not null,
    latitude decimal(8,5) not null,
    longitude decimal(8,5) not null,
    room_type varchar(50) not null,
    price decimal(8,2) not null,
    minimum_nights int not null,
    number_of_reviews int not null,
    last_review date null,
    reviews_per_month int null,
    calculated_host_listings_count int not null,
    availability_365 int not null
);
-- datetime and timestamp both track dates and times. How are they different?
	-- datetime is used for values that contain both day and time parts => YYYY-MM-DD HH:MM:SS.
    -- timestamp is used for values that contain both day and time parts, but also includes the time zone.
-- How does a single character span multiple bytes?
	-- 
-- Which SQL data types correspond to data types in your favorite programming language?
	-- 


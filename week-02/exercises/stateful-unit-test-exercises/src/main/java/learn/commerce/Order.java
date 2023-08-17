package learn.commerce;

import java.util.Arrays;

/**
 * An informal sale contract for purchasable products and services.
 * Products and services are modeled as LineItems.
 * They're added to the order one at a time with the `add` method.
 */
public class Order
{
    private final int orderId;
    private LineItem[] lineItems = new LineItem[0];

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public LineItem[] getLineItems() {
        return lineItems;
    }

    public double getTotal()
    {
        // 1. Complete the getTotal method.
        // It should calculate the order's grand total by summing totals from each LineItem.
        double totalPrice = 0.0f;
        for (int x = 0; x < getLineItems().length; x ++)
        {
            LineItem item = getLineItems()[x];
            totalPrice += item.getTotal();
        }
        return totalPrice;
    }

    public boolean add(LineItem lineItem)
    {
        // invalid item
        if (lineItem == null || lineItem.getQuantity() <= 0 || lineItem.getPrice() < 0.0)
        {
            return false;
        }

        // Extend the array by one and add the lineItem to the end.
        lineItems = Arrays.copyOf(lineItems, lineItems.length + 1);
        lineItems[lineItems.length - 1] = lineItem;

        return true;
    }

    // 2. Stretch goal: add a remove method that removes a LineItem by either index or reference.
    public LineItem[] remove(int index)
    {
        LineItem[] newList = new LineItem[getLineItems().length - 1];
        int addItem = 0;
        for (int x = 0; x < newList.length; x ++)
        {
            if (x != index)
            {
                newList[addItem] = getLineItems()[x];
                addItem += 1;
            }
        }
        return newList;
    }
    public LineItem[] remove(LineItem reference)
    {
        LineItem[] newList = new LineItem[getLineItems().length - 1];
        int addItem = 0;
        for (int x = 0; x < newList.length; x ++)
        {
            if (getLineItems()[x] != reference)
            {
                newList[addItem] = getLineItems()[x];
                addItem += 1;
            }
        }
        return newList;
    }
}

package csulb.cecs343.lair;

public class Element
{
    public String title;
    public int imageID;
    public boolean isSelected;

    Element(String title, int imageID, boolean isSelected)
    {
        this.title = title;
        this.imageID = imageID;
        this.isSelected = isSelected;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public boolean isSelected()
    {
        return isSelected;
    }

    public void setSelected(boolean selected)
    {
        isSelected = selected;
    }
}

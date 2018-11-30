package csulb.cecs343.lair;

public class Element
{
    public String title;
    public int imageID;
    public boolean isSelected;
    public boolean isFolder;
    public boolean isFile;

    Element(String title, int imageID, boolean isSelected, boolean isFolder, boolean isFile)
    {
        this.title = title;
        this.imageID = imageID;
        this.isSelected = isSelected;
        this.isFolder = isFolder;
        this.isFile = isFile;
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

    public boolean isFolder()
    {
        return  isFolder;
    }

    public boolean isFile()
    {
        return isFile;
    }
}

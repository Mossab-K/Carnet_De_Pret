package Model;

import com.sun.deploy.util.ArrayUtil;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Master on 13/03/2017.
 */
public class CarnetModel {

    private String xmlPath = "Carnet.xml";
    private Document document = null;
    private Element rootNode = null;

    public CarnetModel()
    {
        this.initXml();
    }

    private void initXml()
    {
        File xmlFile = new File(this.xmlPath);

        if(!xmlFile.exists())
            this.createXml();
        else
            this.checkXml();
    }

    private void createXml()
    {
        try {

            Element carnet = new Element("carnet");
            Document doc = new Document(carnet);

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(this.xmlPath));

        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    private void checkXml()
    {
        try {

            Element rootNode = this.getRootNode();

            if (!rootNode.getName().equals("carnet"))
            {
                Files.delete(Paths.get(this.xmlPath));
                this.createXml();
            }

        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    public void updateXml()
    {
        try {

            Document doc = this.getDocument();

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(this.xmlPath));

        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    private Document getDocument()
    {
        if (this.document != null)
            return this.document;
        else
        {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(this.xmlPath);

            try {

                this.document = (Document) builder.build(xmlFile);
                this.rootNode = this.document.getRootElement();

            } catch (IOException | JDOMException io) {
                System.out.println(io.getMessage());
            }
        }
        return this.document;
    }

    private Element getRootNode()
    {
        if (this.rootNode != null)
            return this.rootNode;
        else
        {
            if (this.document != null)
                this.rootNode = this.document.getRootElement();
            else
            {
                SAXBuilder builder = new SAXBuilder();
                File xmlFile = new File(this.xmlPath);

                try {

                    this.document = (Document) builder.build(xmlFile);
                    this.rootNode = this.document.getRootElement();

                } catch (IOException | JDOMException io) {
                    System.out.println(io.getMessage());
                }
            }
        }
        return this.rootNode;
    }

    public List<Element> getPersonnes()
    {
        Element rootNode = this.getRootNode();
        return rootNode.getChildren("personne");
    }

    public Element getPersonneByName(String nom)
    {
        List personnesList = this.getPersonnes();

        for (int i = 0; i < personnesList.size(); i++) {

            Element personne = (Element) personnesList.get(i);

            if (personne.getChildText("nom").equals(nom))
                return personne;
        }

        return null;
    }

    public Element getPersonneById(String id)
    {
        List personnesList = this.getPersonnes();

        for (int i = 0; i < personnesList.size(); i++) {

            Element personne = (Element) personnesList.get(i);

            if (personne.getAttribute("id").getValue().equals(id))
                return personne;
        }
        return null;
    }

    public List<Element> getItems()
    {
        List<Element> personnes = this.getPersonnes();
        List<Element> items = new ArrayList<Element>();

        for (int i = 0; i < personnes.size(); i++)
        {
            Element personne = (Element) personnes.get(i);

            List<Element> itemsTmp = (List<Element>) personne.getChild("items").getChildren("item");
            items.addAll(itemsTmp);
        }
        return items;
    }

    public Element getItemById(String id)
    {
        List<Element> items = this.getItems();

        for (int i = 0; i < items.size(); i++)
        {
            Element item = (Element) items.get(i);

            if (item.getAttribute("id").getValue().equals(id))
                return item;
        }
        return null;
    }

    private String createId()
    {
        String uniqueID = UUID.randomUUID().toString().split("-")[0];

        if (this.getPersonneById(uniqueID) != null || this.getItemById(uniqueID) != null)
            this.createId();

        return uniqueID;
    }

    private Element createPersonne(String nom, String prenom)
    {
        Element personne = new Element("personne");
        personne.setAttribute("id", this.createId());
        personne.addContent(new Element("nom").setText(nom));
        personne.addContent(new Element("prenom").setText(prenom));
        personne.addContent(new Element("items"));

        return personne;
    }

    public void addPersonne(String nom, String prenom)
    {
        this.getRootNode().addContent(this.createPersonne(nom, prenom));
        this.updateXml();
    }

    public boolean updatePersonne(String id, String nom, String prenom)
    {
        Element personne = this.getPersonneById(id);

        if (personne != null )
        {
            personne.getChild("nom").setText(nom);
            personne.getChild("prenom").setText(prenom);
            this.updateXml();
            return true;
        }
        return false;
    }

    private Element createItem(String date, String nom, String type)
    {
        Element item = new Element("item");
        item.setAttribute("id", this.createId());
        item.addContent(new Element("nom").setText(nom));
        item.addContent(new Element("type").setText(type));
        item.addContent(new Element("date").setText(date));

        return item;
    }

    public void addItemToPersonne(String idPersonne, String date, String nom, String type)
    {
        Element personne = this.getPersonneById(idPersonne);
        if (personne != null)
        {
            personne.getChild("items").addContent(this.createItem(date, nom, type));
            this.updateXml();
        }
    }

    public boolean updateItem(String id,String date, String nom, String type)
    {
        Element item = this.getItemById(id);

        if (item != null)
        {
            item.getChild("date").setText(date);
            item.getChild("nom").setText(nom);
            item.getChild("type").setText(type);
            this.updateXml();
            return true;
        }
        return false;
    }

    public boolean deleteItem(String id)
    {
        Element item = this.getItemById(id);

        if (item != null)
        {
            Element items = item.getParentElement();
            items.removeContent(item);
            this.updateXml();

            return true;
        }
        return false;
    }

    public boolean deletePersonne(String id)
    {
        Element personne = this.getPersonneById(id);

        if (personne != null)
        {
            this.getRootNode().removeContent(personne);
            this.updateXml();
            return true;
        }
        return false;
    }
}

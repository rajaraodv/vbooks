package com.zimbra.vbooks.ws;



import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.zimbra.vbooks.domain.BookAuthor;
import com.zimbra.vbooks.service.VBooksService;



@Endpoint
public class VBooksEndPoint  {

    private XPath isbnXPath;
    private XPath fullNameXPath;
    private XPath pageNumberXPath;
    private final VBooksService vbooksService;
    private Namespace namespace = Namespace.getNamespace("vbooks", "http://www.zimbra.com/vbooks/schemas");


    public VBooksEndPoint(VBooksService vbooksService) throws JDOMException {
        this.vbooksService = vbooksService;
		isbnXPath = XPath.newInstance("//vbooks:isbn");
		isbnXPath.addNamespace(namespace);
		fullNameXPath = XPath.newInstance("//vbooks:fullName");
		fullNameXPath.addNamespace(namespace);			
		pageNumberXPath = XPath.newInstance("//vbooks:pageNumber");
		pageNumberXPath.addNamespace(namespace);			
		
    }
    @PayloadRoot(localPart = "SaveBookAuthorRequest", namespace = "http://www.zimbra.com/vbooks/schemas")
    @ResponsePayload
    protected Element handleSaveBookAuthorRequest(@RequestPayload Element saveBookAuthorRequest) throws Exception {          
        String isbn = isbnXPath.valueOf(saveBookAuthorRequest);
        String fullName = fullNameXPath.valueOf(saveBookAuthorRequest);
                	
        String resId = vbooksService.saveBook(new BookAuthor(isbn, fullName));
        return getSaveBookAuthorResponse(resId);
    }
    
    private Element getSaveBookAuthorResponse(String resId) {
        Element responseElement = new Element("SaveBookAuthorResponse", namespace);
        responseElement.addContent(new Element("alertId", namespace))
                          .setText(resId);
          
        return responseElement;
     }
    @PayloadRoot(localPart = "GetBookAuthorRequest", namespace = "http://www.zimbra.com/vbooks/schemas")
    @ResponsePayload
    protected Element handleGetBookAuthorRequest(@RequestPayload Element getBookAuthorRequest) throws Exception {          
        int pageNumber = Integer.parseInt(pageNumberXPath.valueOf(getBookAuthorRequest));
                	
       List<BookAuthor> bookAuthors = vbooksService.getBooks(pageNumber);
        return returnGetBookAuthorRequest(bookAuthors);
    }
    private Element returnGetBookAuthorRequest(List<BookAuthor> bookAuthors) {
    	Iterator<BookAuthor> itr = bookAuthors.iterator();
        Element responseElement = new Element("SaveBookAuthorResponse", namespace);
       
    	while(itr.hasNext()){
    		BookAuthor ba = itr.next();
    		String isbn = String.valueOf(ba.getIsbn());
    		String fullName = ba.getFullName();   
    		Element baElement = new Element("BookAuthor");
    		baElement.addContent((new Element("isbn")).setText(isbn)); 
    		baElement.addContent((new Element("fullName")).setText(fullName));    		
    		responseElement.addContent(baElement); 
    	}
        return responseElement;
     }
    @PayloadRoot(localPart = "DeleteBookAuthorRequest", namespace = "http://www.zimbra.com/vbooks/schemas")
    @ResponsePayload
    protected Element handleDeleteBookAuthorRequest(@RequestPayload Element deleteBookAuthorRequest) throws Exception {          
    	String isbn = isbnXPath.valueOf(deleteBookAuthorRequest);
        String fullName = fullNameXPath.valueOf(deleteBookAuthorRequest);
                	
        String resId = vbooksService.deleteBook(new BookAuthor(isbn, fullName));
        return returnDeleteBookAuthorResponse(resId);        
    }  
    
    private Element returnDeleteBookAuthorResponse(String resId) {
        Element responseElement = new Element("DeleteBookAuthorResponse", namespace);
        responseElement.addContent(new Element("alertId"))
                          .setText(resId);
          
        return responseElement;
     } 
    
}

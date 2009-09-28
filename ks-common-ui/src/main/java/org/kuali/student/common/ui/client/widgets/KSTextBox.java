package org.kuali.student.common.ui.client.widgets;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.TextBox;

/**
 * KSTextArea wraps gwt TextArea.  This class provides most of the same functionality, but sets KS css styles
 * for its default look and a variety of TextArea events (for improved browser compatibility and customizability).
 * 
 * @author Kuali Student Team
 *
 *
 */
public class KSTextBox extends TextBox{

    /**
     * Creates an empty text box.
     * 
     */
    public KSTextBox() {
        super();
        setupDefaultStyle();
    }

    /**
     *  Creates a new text box using the text box element specified.
     * 
     * @param element a <input> element of type 'text'
     */
    public KSTextBox(Element element) {
        super(element);
        setupDefaultStyle();
    }
    
    /**
     * This method sets the default style for the text box and text box events.
     * 
     */
    private void setupDefaultStyle() {
        addStyleName(KSStyles.KS_TEXTBOX_STYLE);
        
        this.addBlurHandler(new BlurHandler(){
            public void onBlur(BlurEvent event) {
                KSTextBox.this.removeStyleName(KSStyles.KS_TEXTBOX_FOCUS_STYLE);
                
            }   
        }); 

        this.addFocusHandler(new FocusHandler(){
            public void onFocus(FocusEvent event) {
                KSTextBox.this.addStyleName(KSStyles.KS_TEXTBOX_FOCUS_STYLE);

            }       
        });
        
        this.addMouseOverHandler(new MouseOverHandler(){
            public void onMouseOver(MouseOverEvent event) {
                KSTextBox.this.addStyleName(KSStyles.KS_TEXTBOX_HOVER_STYLE);
                
            }       
        });
        
        this.addMouseOutHandler(new MouseOutHandler(){

            public void onMouseOut(MouseOutEvent event) {
                KSTextBox.this.removeStyleName(KSStyles.KS_TEXTBOX_HOVER_STYLE);
                
            }
            
        });
        
    }
}

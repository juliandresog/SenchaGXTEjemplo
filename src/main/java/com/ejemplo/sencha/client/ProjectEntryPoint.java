package com.ejemplo.sencha.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ProjectEntryPoint implements EntryPoint {

    @Override
  public void onModuleLoad() {
      FormularioUsuarios form = new FormularioUsuarios();
      RootPanel.get().add(form);
  }
  
//  @Override
//  public void onModuleLoad() {
//    String version = GXT.getVersion().getRelease();
//    TextButton textButton = new TextButton("Verify GXT Works: Version=" + version);
//    RootPanel.get().add(textButton);
//    textButton.addSelectHandler(new SelectHandler() {
//      @Override
//      public void onSelect(SelectEvent event) {
//        MessageBox messageBox = new MessageBox("GXT Works.");
//        messageBox.show();
//      }
//    });
//    
//    GWTServiceUsageExample rpcExample = new GWTServiceUsageExample();
//    RootPanel.get().add(rpcExample);
//    
//  }
  
}

package com.ejemplo.sencha.client;

import com.google.gwt.junit.client.GWTTestCase;

public class CompileGwtTest extends GWTTestCase {
  
  @Override
  public String getModuleName() {
    return "com.ejemplo.sencha.Project";
  }

  public void testSandbox() {
    assertTrue(true);
  }
  
}

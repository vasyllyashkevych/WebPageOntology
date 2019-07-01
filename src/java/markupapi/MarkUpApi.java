/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markupapi;
/** The object model for the data we are sending through endpoints */
public class MarkUpApi {

  private String myData = "MarkUp API";

  public String getData() {
    return myData;
  }

  public void setData(String data) {
    myData = data;
  }
}
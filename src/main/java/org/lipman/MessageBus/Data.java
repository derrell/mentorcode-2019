/*
 * Copyright:
 *   2019 Derrell Lipman
 *
 * License:
 *   LGPL: http://www.gnu.org/licenses/lgpl.html
 *
 * Authors:
 *   Derrell Lipman (derrell)
 */

package org.lipman.MessageBus;

import org.lipman.MessageBus.EDataType;

public class Data extends Object
{
  private EDataType     type;
  private Double        d;
  private String        s;
  
  public Data(int i)
  {
    this.set(i);
  }
  
  public Data(double d)
  {
    this.set(d);
  }
  
  public Data(String s)
  {
    this.set(s);
  }
  
  void set(int i)
  {
    this.set(i + 0.0);
  }
  
  void set(Double d)
  {
    this.type = EDataType.NUMBER;
    this.d = d;
  }
  
  void set(String s)
  {
    this.type = EDataType.STRING;
    this.s = s;
  }
  
  public double getNumber()
  {
    return this.d;
  }
  
  public String getString()
  {
    return this.s;
  }
  
  public EDataType getType()
  {
    return this.type;
  }
}

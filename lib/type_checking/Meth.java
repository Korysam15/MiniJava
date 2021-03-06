/*  MiniJava type checking system
 *  Copyright (C) 2014  marklrh
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

import syntaxtree.*;
import java.util.*;

/* Claim: I don't do drugs */
public class Meth
{
  public String id;
  public Type type;
  public ArrayList<Var> parameters;
  public HashMap<String, Var> local_vars;

  public Meth(String id, Type t)
  {
    this.id = id;
    this.type = t;
    parameters = new ArrayList<Var>();
    local_vars = new HashMap<String, Var>();
  }

  public String getId() {return id;}

  public Type getType() {return type;}

  public ArrayList<Var> getParameters() {return parameters;}

  // parameters
  public boolean containsPara(String id)
  {
    for(Var v : parameters)
    {
      if(v != null)
        if(v.getId().equals(id))
          return true;
    }
    return false;
  }

  public boolean addParameter(String id, Type t)
  {
    if(containsPara(id))
      return false;
    else
    {
      parameters.add(new Var(id, t));
      return true;
    }
  }

  public Var getParameter(String id)
  {
    for(Var v : parameters)
    {
      if(v != null)
        if(v.getId().equals(id))
          return v;
    }
    return null;
  }

  // local variables
  public boolean containslv(String id)
  {
    return (containsPara(id) || local_vars.containsKey(id));
  }

  public boolean addLocalVar(String id, Type t)
  {
    if(containslv(id))
      return false;
    else
    {
      local_vars.put(id, new Var(id, t));
      return true; 
    }
  }

  public Var getLocalVar(String id)
  {
    return local_vars.get(id);
  }

}
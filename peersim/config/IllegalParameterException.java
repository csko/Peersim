/*
 * Copyright (c) 2003 The BISON Project
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 */

package peersim.config;

/**
 * 
 *
 * @author Alberto Montresor
 * @version $Revision: 1.1 $
 */
public class IllegalParameterException
extends RuntimeException
{

	/**
	 * 
	 */
	public IllegalParameterException(String name, String message, Exception e)
	{
		super("Parameter \"" + name + "\": " + message +  
		" [at " + 
		e.getStackTrace()[1].getClassName()+"."+
		e.getStackTrace()[1].getMethodName()+":"+
		e.getStackTrace()[1].getLineNumber() + "]");
		//e.printStackTrace();
	}

	/**
	 * 
	 */
	public IllegalParameterException(String name, String message)
	{
		super("Parameter \"" + name + "\": " + message);
	}

}

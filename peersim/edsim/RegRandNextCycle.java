/*
 * Copyright (c) 2003-2005 The BISON Project
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

package peersim.edsim;

import peersim.config.*;
import peersim.core.*;


/**
* Implements a random delay, but making sure there is exactly one call in each
* consequtive <code>step</code> time units.
*/
public class RegRandNextCycle extends NextCycleEvent {

// ============================== fields ==============================
// ====================================================================

/**
* Indicates the start of the next cycle for a particular protocol
* instance. If negative it means it has not been initialized yet.
*/
private long nextCycleStart = -1;

// =============================== initialization ======================
// =====================================================================


/**
* Calls super constructor.
*/
public RegRandNextCycle(String n, Object obj) {

	super(n,obj);
}

// --------------------------------------------------------------------

/**
* Calls super.clone().
*/
protected Object clone() throws CloneNotSupportedException {
	
	return super.clone();
}


// ========================== methods ==================================
// =====================================================================


/**
* Returns a random delay but making sure there is exactly one invocation in each
* consequtive interval of length <code>step</code>. The beginning of these
* intervals is defined by the first invocation which is in turn defined by
* {@link CDScheduler} that initiates the protocol in question.
*/
protected long nextDelay() {
	
	// at this point nextCycleStart points to the start of the next cycle
	// (the cycle after the one in which this execution is taking place)
	// (note that the start of the cycle is included in the cycle)
	
	final long now = CommonState.getTime();
	if(nextCycleStart<0)
	{
		// not initialized
		nextCycleStart=now+sch.step;
	}
	
	// to be on the safe side, we do the next while loop.
	// although currently it never executes
	while(nextCycleStart<=now) nextCycleStart+=sch.step;
	
	// we increment nextCycleStart to point to the start of the cycle
	// after the next cycle
	nextCycleStart+=sch.step;
	
	return nextCycleStart-now-CommonState.r.nextLong(sch.step)-1;
}

}



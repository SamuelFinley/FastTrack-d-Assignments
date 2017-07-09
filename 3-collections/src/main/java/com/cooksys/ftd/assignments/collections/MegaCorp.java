package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */

	private Map<FatCat, Set<Capitalist>> hierarch = new HashMap<FatCat, Set<Capitalist>>();
	private Set<Capitalist> set = new HashSet<Capitalist>();
    @Override
    public boolean add(Capitalist capitalist) {
    		if (set.contains(capitalist) || capitalist == null) {
    			return false;
    		}
    		if (capitalist.hasParent()) {
    			//System.out.println(1);
    			this.add(capitalist.getParent());
    			set.add(capitalist.getParent());
    			return set.add(capitalist);
    		} else if ( capitalist instanceof FatCat) {
    			//System.out.println(2);
    			return set.add(capitalist);
    		} else {
    			return false;
    		}
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
    	return set.contains(capitalist);
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
    	Set<Capitalist> result = new HashSet<Capitalist>(set);
    	return result;
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
    	Set<FatCat> capSet = new HashSet<FatCat>();

    	for (Capitalist caps : set) {
    		if (caps instanceof FatCat) {
    			capSet.add((FatCat)caps);
    		}
    	}
    	return new HashSet<FatCat>(capSet);
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	HashSet<Capitalist> capSet = new HashSet<Capitalist>();
    	if (!this.set.isEmpty()){
    		for (Capitalist babs : this.set) {
    			if (babs.hasParent()) { 
    				if (babs.getParent()==fatCat)
    				capSet.add(babs);
    			}
    		}
    	}
    	return new HashSet<Capitalist>(capSet);
    }
    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	Map<FatCat, Set<Capitalist>> hierarch = new HashMap<FatCat, Set<Capitalist>>();
    		for (FatCat babs : this.getParents()) {
    				hierarch.put((FatCat) babs, getChildren(babs));
    			}
    	return hierarch;
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
    	ArrayList<FatCat> parent = new ArrayList<FatCat>();
    		Capitalist frown = capitalist;
    		if (capitalist != null && !set.isEmpty()){
    			while (frown.hasParent()) {
    				parent.add(frown.getParent());
    				frown = frown.getParent();
    			}
    		}
    	return parent;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hierarch == null) ? 0 : hierarch.hashCode());
		result = prime * result + ((set == null) ? 0 : set.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MegaCorp other = (MegaCorp) obj;
		if (hierarch == null) {
			if (other.hierarch != null)
				return false;
		} else if (!hierarch.equals(other.hierarch))
			return false;
		if (set == null) {
			if (other.set != null)
				return false;
		} else if (!set.equals(other.set))
			return false;
		return true;
	}


}

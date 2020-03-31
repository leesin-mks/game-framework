/*
 * Tree
 *
 * 2019年7月3日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.bdsk.struct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author dsfish
 *
 */
public class Tree<E> implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected E nodeEntity;
    protected Tree<E> parentNode;
    protected List<Tree<E>> childNodes;

    transient Set<E> entrySet;

    public Tree(E nodeEntity)
    {
        this.nodeEntity = nodeEntity;
    }

    /**
     * @return the nodeEntity
     */
    public E getNodeEntity()
    {
        return nodeEntity;
    }

    public void addChildren(Tree<E> children)
    {
        synchronized (nodeEntity)
        {
            Objects.requireNonNull(children);
            children.setParent(this);
            if (this.childNodes == null)
            {
                this.childNodes = new ArrayList<>();
            }
            this.childNodes.add(children);
        }
    }

    public void removeChildren(Tree<E> children)
    {
        synchronized (nodeEntity)
        {
            Objects.requireNonNull(children);
            if (this.childNodes != null)
            {
                this.childNodes.remove(children);
            }
        }
    }

    public void setParent(Tree<E> parent)
    {
        this.parentNode = parent;
    }

    public Tree<E> getParent()
    {
        return parentNode;
    }

    public boolean isRoot()
    {
        return parentNode == null;
    }

    public Set<Tree<E>> entrySet()
    {
        synchronized (nodeEntity)
        {
            Set<Tree<E>> es = new HashSet<>();
            if (hashChildren())
            {
                treeDLR(this, es);
            }
            es.add(this);
            return es;
        }
    }

    public List<Tree<E>> entryList()
    {
        List<Tree<E>> es = new ArrayList<>();
        if (hashChildren())
        {
            treeDLR(this, es);
        }
        es.add(this);
        return es;
    }

    public boolean hashChildren()
    {
        return childNodes != null && childNodes.size() > 0;
    }

    public List<Tree<E>> getChildren()
    {
        return childNodes;
    }

    private void treeDLR(Tree<E> tree, Collection<Tree<E>> es)
    {
        for (Tree<E> e : tree.childNodes)
        {
            if (e.hashChildren())
            {
                treeDLR(e, es);
            }
            es.add(e);
        }
    }

}

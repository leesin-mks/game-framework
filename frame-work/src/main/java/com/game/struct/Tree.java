/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.struct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author leesin
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

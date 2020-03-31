/*
 * FixedLengthLinkedList
 *
 * 2017年10月21日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.bdsk.struct;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Kissy
 *
 */
public class FixedLengthLinkedList<T>extends LinkedList<T>
{
    private static final long serialVersionUID = 4973392114509742343L;

    private static final int DEFAULT_MAX_SIZE = 50;

    private int maxSize;

    public FixedLengthLinkedList()
    {
        this(DEFAULT_MAX_SIZE);
    }

    public FixedLengthLinkedList(int maxSize)
    {
        this.maxSize = maxSize;
    }

    /**
     * @return the maxSize
     */
    public int getMaxSize()
    {
        return maxSize;
    }

    @Override
    public boolean add(T e)
    {
        boolean ret = super.add(e);
        if (this.size() > maxSize)
            this.removeFirst();
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.LinkedList#addFirst(java.lang.Object)
     */
    @Override
    public void addFirst(T e)
    {
        super.addFirst(e);
        if (this.size() > maxSize)
            this.removeLast();
    }

    @Override
    public void addLast(T e)
    {
        super.addLast(e);
        if (this.size() > maxSize)
        {
            this.removeFirst();
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        int outOf = this.size() + c.size() - maxSize;
        if (outOf > 0)
        {
            outOf = outOf > maxSize ? maxSize : outOf;
            int offset = 1;
            Iterator<? extends T> it = c.iterator();
            while (it.hasNext())
            {
                super.addLast(it.next());
                super.removeFirst();
                if (offset >= outOf)
                {
                    break;
                }
                offset++;
            }
            return true;
        }
        else
        {
            return super.addAll(c);
        }
    }

    public void add(int index, T element)
    {
        super.add(index, element);
        if (this.size() == maxSize + 1)
        {
            if (index == 0)
            {
                super.removeLast();
            }
            else
            {
                super.removeFirst();
            }
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        int outOf = this.size() + c.size() - maxSize;
        if (outOf > 0)
        {
            outOf = outOf > maxSize ? maxSize : outOf;
            int offset = index;
            Iterator<? extends T> it = c.iterator();
            while (it.hasNext())
            {
                super.add(index++, it.next());
                super.removeLast();
                if (offset >= maxSize)
                {
                    break;
                }
                offset++;
            }
            return true;
        }
        else
        {
            return super.addAll(index, c);
        }
    }
}

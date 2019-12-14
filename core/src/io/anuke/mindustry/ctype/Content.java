package io.anuke.mindustry.ctype;

import io.anuke.arc.util.ArcAnnotate.*;
import io.anuke.mindustry.*;
import io.anuke.mindustry.mod.*;
import io.anuke.mindustry.type.*;


/** Base class for a content type that is loaded in {@link io.anuke.mindustry.core.ContentLoader}. */
public abstract class Content implements Comparable<Content>{
    public final short id;
    /** Info on which mod this content was loaded from. */
    public @Nullable ModContentInfo minfo;


    public Content(){
        this.id = (short)Vars.content.getBy(getContentType()).size;
        Vars.content.handleContent(this);
    }

    /**
     * Returns the type name of this piece of content.
     * This should return the same value for all instances of this content type.
     */
    public abstract ContentType getContentType();

    /** Called after all content and modules are created. Do not use to load regions or texture data! */
    public void init(){
    }

    /**
     * Called after all content is created, only on non-headless versions.
     * Use for loading regions or other image data.
     */
    public void load(){
    }

    /** @return whether an error ocurred during mod loading. */
    public boolean hasErrored(){
        return minfo != null && minfo.error != null;
    }

    @Override
    public int compareTo(Content c){
        return Integer.compare(id, c.id);
    }

    @Override
    public String toString(){
        return getContentType().name() + "#" + id;
    }
}

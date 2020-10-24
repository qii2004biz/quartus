package ui.controls;

import ui.SubItem;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target ( ElementType.FIELD )
@Retention ( value = RetentionPolicy.RUNTIME )
public @interface SubItems {
    SubItem[] value();
}

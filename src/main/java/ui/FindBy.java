package ui;

import core.Platform;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention ( value = RetentionPolicy.RUNTIME )
@Repeatable ( FindByList.class )
public @interface FindBy {
    String locator();
    Platform platform() default Platform.ANY;
}

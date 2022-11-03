package com.algaworks.algafood.core.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MultiploValidator.class })
public @interface Multiplo {

   
   public abstract java.lang.String message() default "{Multiplo}";
  
   public abstract  java.lang.Class<?>[] groups() default {};
  
   public abstract  java.lang.Class<? extends javax.validation.Payload>[] payload() default {};

   int numero();
   
}

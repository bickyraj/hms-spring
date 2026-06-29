package com.hms.common;

public abstract class UseCase<Req, Res> {
     public Res execute(Req request) {
         return null;
     }

     public Res execute() {
       return execute(null);
     }

     public static class Empty {}
}
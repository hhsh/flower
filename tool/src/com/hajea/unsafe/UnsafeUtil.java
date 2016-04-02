///*
// * Copyright (c) 2015-2016, Christoph Engelbert (aka noctarius) and
// * contributors. All rights reserved.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.hajea.unsafe;
//
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Field;
//import java.nio.ByteOrder;
//import java.security.AccessController;
//import java.security.PrivilegedAction;
//import sun.misc.Unsafe ;
//
//public final class UnsafeUtil {
//	 
//	public static void main(String[] args) throws Exception {
//		 Unsafe unsafe = findUnsafe();
//		 long a = unsafe.allocateMemory(8);
//	        try {
//	            unsafe.putLong(a, 0x0102030405060708L);
//	            byte b = unsafe.getByte(a);
//	            switch (b) {
//	            case 0x01: System.out.println("大端  BIG_ENDIAN");     break;
//	            case 0x08: System.out.println("小端 LITTLE_ENDIAN");   break;
//	            default:
//	                assert false;
//	            }
//	        } finally {
//	            unsafe.freeMemory(a);
//	        }
//	}
//
//    public static final Unsafe UNSAFE;
//
////    public static final long OBJECT_ARRAY_BASE;
////    public static final long OBJECT_ARRAY_SHIFT;
////
////    public static final long IDENTIFIER_DATA_OFFSET;
//
//    static {
//        Unsafe unsafe;
//
//        try {
//            unsafe = findUnsafe();
//        } catch (RuntimeException e) {
//            unsafe = null;
//        }
//        if (unsafe == null) {
//            throw new RuntimeException("Incompatible JVM - sun.misc.Unsafe support is missing");
//        }
//
////        try {
////            Field identifierData = Identifier.class.getDeclaredField("data");
////            identifierData.setAccessible(true);
////            IDENTIFIER_DATA_OFFSET = unsafe.objectFieldOffset(identifierData);
////
////            OBJECT_ARRAY_BASE = unsafe.arrayBaseOffset(Object[].class);
////            int indexScale = unsafe.arrayIndexScale(Object[].class);
////            OBJECT_ARRAY_SHIFT = 31 - Integer.numberOfLeadingZeros(indexScale);
////
////        } catch (ReflectiveOperationException e) {
////            throw new IllegalStateException();
////        }
//
//        UNSAFE = unsafe;
//    }
//
//    private static Unsafe findUnsafe() {
//        try {
//            return Unsafe.getUnsafe();
//        } catch (SecurityException se) {
//            return AccessController.doPrivileged(new PrivilegedAction<Unsafe>() {
//                
//                @SuppressWarnings("restriction")
//				public Unsafe run() {
//                    try {
//                        Class<Unsafe> type = Unsafe.class;
//                        try {
//                            Field field = type.getDeclaredField("theUnsafe");
//                            field.setAccessible(true);
//                            return type.cast(field.get(type));
//
//                        } catch (Exception e) {
//                            for (Field field : type.getDeclaredFields()) {
//                                if (type.isAssignableFrom(field.getType())) {
//                                    field.setAccessible(true);
//                                    return type.cast(field.get(type));
//                                }
//                            }
//                        }
//                    } catch (Exception e) {
//                        throw new RuntimeException("Unsafe unavailable", e);
//                    }
//                    throw new RuntimeException("Unsafe unavailable");
//                }
//            });
//        }
//    }
//
//    private UnsafeUtil() {
//    }
//
//}

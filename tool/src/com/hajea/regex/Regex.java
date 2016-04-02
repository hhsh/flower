//package com.hajea.regex;
//
//import java.util.regex.MatchResult;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Regex {
//
//	public static void main(String[] args) {
//		main1() ;
//		//Unicode ��9M�_��9M
////		Pattern p = Pattern.compile("\\\\"); 
////		Matcher matcher = p.matcher("\u005C\u005C");
////		if(matcher.matches()) {
////			String pathName = matcher.group(0);   
////			System.out.println(pathName);
////		}
//		
//		
//		// ^.*?(\\d*).*?$ :�H     
// 
////		Pattern p = Pattern.compile(".*?\\Q110\\E.*?"); 
////		System.out.println(Pattern.quote("\\Q110\\E"));
////		Matcher matcher = p.matcher("�110`�120�130");
////		System.out.println(matcher.hitEnd());
////		if(matcher.lookingAt()){
////			System.out.println(matcher.start() + " " + matcher.end());
////		}
////		if(matcher.matches()){
////			String pathName = matcher.group(0);   
////			System.out.println(pathName);
////		}
////		Pattern p = Pattern.compile(".*?(\\d+).*?"); 
////		Matcher matcher = p.matcher("�110`�120�130");
////		
////		if(matcher.matches()){
////			String pathName = matcher.group(1);   
////			System.out.println(pathName);
////		}
////			
////		MatchResult  matchRs = matcher.toMatchResult();
////		System.out.println(matchRs.end());
//		String sd  = "\u005c\u0027";
//		System.out.println(sd);
//		String ch  = "\u0066\u0303\u0069\u0323";
//		System.out.println(ch);
//		//String s = "�";
//		 
//		 
//		  
//		
//		// (-��-�{lI�CW&	\`
//		String r = "\\\\"; // [\\,\\] /\\/
//		String s = "\\";  //  [\]
//		
//		Pattern p = Pattern.compile(r);
//		Matcher m = p.matcher(s);
//		boolean b = m.matches();
//		System.out.println(b);
//		if(b)
//			System.out.println("match:["+m.group()+"]");
//
//	}
//	
//	
//	public static void main1()  
//    {  
//        String regex = "([a-z]|//d)*";  
//        String inputStr = "";  
//        for (int i = 0; i < 309; i++) //d�<:>=400l�8  
//        {  
//            inputStr = inputStr.concat(String.valueOf(i));  //������eW&2  
//        }  
//        System.out.println("W&2�:"+inputStr.length());  
//        boolean flag = checkSpecialChars(inputStr, regex);  
//        System.out.println("9M�:: "+flag);  
//    }  
//    public static boolean checkSpecialChars(String inputstr, String regex)  
//    {  
//        if (inputstr == null || "".equals(inputstr))  
//        {  
//            return false;  
//        }  
//        return Pattern.compile(regex).matcher(inputstr).matches(); //�/dmatches()�[�8  
//    }  
//    
//    
//
//}
//.equals(inputstr))  
//        {  
//            return false;  
//        }  
//        return Pattern.compile(regex).matcher(inputstr).matches(); //�/dmatches()�[�8  
//    }  
//    
//    
//
//}
//   }  
//    
//    
//
//}
  
//    
//    
//
//}
//   }  
//    
//    
//
//}

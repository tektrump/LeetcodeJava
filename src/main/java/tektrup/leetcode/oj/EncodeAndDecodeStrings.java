package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tektrup.leetcode.util.annotations.Leetcode;
import tektrup.leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-05-29", tags={Tags.SERIALIZE}, url="https://leetcode.com/problems/encode-and-decode-strings/")
public abstract class EncodeAndDecodeStrings {
	public abstract String encode(List<String> strs);
	public abstract List<String> decode(String s);
	public static void main(String[] args) {
		EncodeAndDecodeStrings instance = new SolutionII();
		List<String> strs, strs1;
		
		strs = Arrays.asList(new String[]{"asdf", "dfegg"});
		strs1 = instance.decode(instance.encode(strs));
		System.out.println(strs.equals(strs1));
		
		strs = Arrays.asList(new String[]{"Ux(=xwocAXrk!L+{XN&$%t|-us1R]i*EzeO)wg|Tk7D^","u.`aS~P#&bwWwav.N.(&SU9<:z$8z:H*<V:(2n/I=sa!l1pQ&y$Lt42P6vaMZL0V~f6Vfq8y,U0},81K[@w(","^-_z7Ag> bFL!GI_v_tWF$g)B&:qrGR]m&slqZN:5IX((5YY$","]u(Og Ys_Qk+&WRwZ{PsAwJ+$RHyR!B-(#;hjiq-,<IK^~VK=].6kJ4Md4X;dnTHA9CXB<E3a)xz1ga~J_n?LBOpe9!>","B4d([Ycp<4`Tg)c*8D<k$:O-S*bm0T-B^{ghSdQrU$fJu=S.(9>+k7`Jm[m @Lt1ZTHnW_$P[D81xk0","}z=[-elVi~x#vc#U<Y3Iqc`0C)_.=%6C?y.|i[) f_+kEAhPP,P=DD+spuIQ,Ye","n9qL<g_YWV%q+_Yx|^sD`Yn`Jm6?>dw8wr(M~K7j<[oB{#_4Eh@`.s+QU!`-ZbrITU1*r<>O!)","EeTgT=,O 0","5}{w+C|j*#ekBP-)>jnOv3<>Se!xH^7Fh&4K#Su|o_/UGyK(cKyIE/|5OteHvPnCfRROc[$=TbS@Ab_k;-eEYgg4Ie4FZ`[tc","&w_&G*@Kj@Rs0-R4R#B<]eNC*TD;5g_`3","d{7f/`{L~R{hcn?","hC:6Dg{zs0e8./]>;YI0+F?z]aytB/tvTmBw0%9X IAyx,d","4av}Ca)A`xsrzPdq/k>]0JsNVyRJ==tzzqh:wQ3 (bQ}","-UgTItj.vMoD/LJM}!ecEA~|rnwJ&nucQ}@!S#qDUL*bw,^B$E=CX[s27o:7?sJou-yv;)h^A@l<e=tT>oRgkj:wX","}}/vo*IzO>aOnkb)O{:os0TR75K;Z~G<CTR!","m<M_",")l&<dFd(^%S3[Y`nk|_f`h5HP?B+4B[~`GQY9u(Z2XOXd,dX,({sxR-{:G<$S|oitXW5:/j4c!g>awl+","3weDEbdhi-`?3NG+O=qP3=xX#kS[2`@y(]IMGv#Tq;Nm6?y.c_fg}W@%_*MeE|mflPy+z=z8E@P*c8*(.","v/bS}5(-jc`5k$=T)b(;9K1`GT}rC","m;6~0G>&k,/ FT8n0pvVa<:/{$`psiJHOS{)7}DRxJ+9/BbWA,dka9{nv(F[^0#(<o.~V+C:QbpO7Cy<;=%O}","h1PJ38`*+$nvU>Qa8p;{vqxx8[;`JOOtH5kF*Lk5vB3-5*+GVAf:S1f0*1ck1/f2","H;QxI3#0t{TV!4qc,!J7ZU4*c`I+mzzi/O]F{w~4KYzC6ne$!|+1R|{:wXxcF9E","-(z;[mm8V6ABbd=cx4Ix=nL1P;*C=v[[l;F_!.j3>1Y.WM(Pbgl18b5FM:Xg`$5{ySc5s`){DIVY6bUA","=0RWT*g|<[(+Ncu=yugSNz","j5po7u{J!YCOcV}69^cvDKvS?JAQpFpzgysr8loThgvA6","4R=F+h9#[3","[)N$:@iDhT:wEA,CL{BEPt|&TJB+j4Q8JyImJjl;;kNt<;Z``<yj^E|>;&#bW7&5]s+Pv1&W","7&bo","Uewog?UhfIdow3PU5DRa`o+_P,Y2NEhV8goTD@8xx;[GJ{zTkgg`|%<djx`[g-ynDr6",";,s&c}owq,$WRE^C{@eqCkmO/#8*;+ 6u3x<W{0Z7|Yx2c&Xh5h/ak*.2d[&i$O^x#r8@:e$1,lOsmb0Il{|-{%&"});
		strs1 = instance.decode(instance.encode(strs));
		System.out.println(strs.equals(strs1));
		
		strs = Arrays.asList(new String[]{";"});
		strs1 = instance.decode(instance.encode(strs));
		System.out.println(strs.equals(strs1));
	}
	
	// Solution III: Best
	// instead of using break delimiter, we specify length of lines at the beginning.
    // this trick saves us from delimiter as we can tell the end of string with given length.
    // potentially save space as we don't have to double the original delimiter character in a string;
    // and with length given, we can directly extract the string from encoded string using substring().
	public static class Solution extends EncodeAndDecodeStrings {
		public String encode(List<String> strs) {
	        StringBuilder builder = new StringBuilder();
	        for (String str : strs) {
	            builder.append(str.length()).append(".");
	            builder.append(str);
	        }
	        return builder.toString();
	    }
	    
	    public List<String> decode(String s) {
	        List<String> results = new ArrayList<>();
	        int i = 0;
	        while (i < s.length()) {
	            int len = 0;
	            while (s.charAt(i) != '.') {
	                len = len*10 + (s.charAt(i) - '0');
	                i++;
	            }
	            i++;
	            results.add(s.substring(i, i + len)); // directly extract instead of builder.append().
	            i += len;
	        }
	        return results;
	    }
	}
	
	// Solution II: Accepted
	// use special character with delimiter to mark a "break" between 2 strings.
	// as a result, we must modify the original break character the same way we deal with "\" and special char in Java.
	// we design our code so that the delimiter and break can be set as constant. 
	// NOTE: delimit and break character must NOT be the same.
	// previous i use ';' as break, and modify original ';' character to ';;'. this won't work:
	// example: if i encounter ';;;' when decoding, it can be interpreted as break + ';' or ';' + break.
	//
	// use a special character with delimiter to represent line break;
	// if a string contains a char which is the same as delimiter, we double it.
	// if we encounter a char '\', it MUST BE FOLLOWED BY ANOTHER CHAR:
	// 		if it's followed by another '\', translate '\\' to a '\' character;
	//		otherwise, it must be followed by '\n', which marks a "break" of string in the string list.
	// this is similar design as special character syntax in Java.
	public static class SolutionII extends EncodeAndDecodeStrings {
		private static final char DELIMIT = '\\'; // --> SYNTAX: must use double backslash for backslash character.
	    private static final char BREAK = 'n';
	    public String encode(List<String> strs) {
	        if (strs == null || strs.isEmpty())
	            return "";
	        StringBuilder builder = new StringBuilder();
	        for (String str : strs) {
	            for (char ch : str.toCharArray()) {
	                if (ch == DELIMIT)
	                    builder.append(DELIMIT);
	                builder.append(ch);
	            }
	            builder.append(DELIMIT).append(BREAK); 	// we add a line break even for the last string;
	            										// a little bit cost of memory, but simplify codes.
	        }
	        return builder.toString();
	    }
	    
	    public List<String> decode(String s) {
	        if (s.isEmpty())
	            return Collections.emptyList();
	        List<String> results = new ArrayList<>();
	        StringBuilder builder = new StringBuilder();
	        for (int i = 0; i < s.length(); i++) {
	            char ch = s.charAt(i);
	            if (ch == DELIMIT) {
	                if (s.charAt(i+1) == DELIMIT) {
	                    builder.append(DELIMIT);
	                    i++;
	                } else {
	                    results.add(builder.toString());
	                    builder = new StringBuilder();
	                    i++;
	                }
	            } else {
	                builder.append(ch);
	            }
	        }
	        return results;
	    }
	}
	
	
	// Solution I: Logic Error
	/*
	public static class SolutionI extends EncodeAndDecodeStrings {
		private static final char delimit = ';';
	    private static final char empty = '-';
	    public String encode(List<String> strs) {
	        StringBuilder builder = new StringBuilder();
	        for (int i = 0; i < strs.size(); i++) {
	            String str = strs.get(i);
	            if (str.isEmpty()) {
	                builder.append(empty);
	            } else {
	                for (char ch : str.toCharArray()) {
	                    if (ch == delimit || ch == empty)
	                        builder.append(ch); // double special chars
	                    builder.append(ch);
	                }
	            }
	            builder.append(delimit);
	        }
	        return builder.toString();
	    }

	    // Decodes a single string to a list of strings.
	    public List<String> decode(String s) {
	        List<String> results = new LinkedList<>();
	        int i = 0;
	        while (i < s.length()) {
	            // build 1 line at each loop
	            StringBuilder builder = new StringBuilder();
	            boolean stop = false;
	            while (!stop && i < s.length()) {
	                char ch = s.charAt(i);
	                if (ch == delimit || ch == empty) {
	                    if (!isSingle(ch, s, i)) {
	                        builder.append(ch); // 2 special chars is 1 regular char
	                        i += 2;
	                    } else {
	                        if (ch == delimit) {
	                            results.add(builder.toString());
	                            i++;
	                            stop = true;
	                            continue;
	                        } else { // empty
	                            i++;
	                        }
	                    }
	                } else {
	                    builder.append(ch);
	                    i++;
	                }
	            }
	        }
	        return results;
	    }
	    
	    private boolean isSingle(char ch, String s, int i) {
	        int count = 1;
	        while (i+1 < s.length() && s.charAt(i+1) == ch) {
	            count++;
	            i++;
	        }
	        return count % 2 == 1;
	    }
		
	}
	*/
}

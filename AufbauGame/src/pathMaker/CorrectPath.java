package pathMaker;

import debug.EclipsePath;

public class CorrectPath {

	/*
	 *  Just for fixing some Path issues
	 *  
	 *  If eclipse is used than the syntax ./ is used without declaring it
	 *  when you declaring it eclipse doesnt know how to handle the path correctly
	 *  but if eclipse isnt used you need to declare relative Positions
	 *  
	 *  @Debug
	 */
	
	public String checkPath(String Path) {
		if(EclipsePath.isEclipse())
			return Path.replace("../", "");
		System.out.println(EclipsePath.isEclipse());
		return Path;
	}
}

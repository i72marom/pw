import java.util.ArrayList;

public abstract class GeneradorDeAnuncio {
	/** 
	 * Create a menu for a weekday with several dishes
	 * @return A list of the dishes included in the menu
	 *  */
	public abstract ArrayList<DailyMeal> createWeekMenu();
	
	/**
	 * Create a menu as a season meal
	 * @return A season meal for the menu
	 * */
	public abstract SeasonMeal createSeasonMenu();
}
package ap2016.entities;


/**
 * Enumerator used to handle the roles of the user.
 * @author Matteo Nardini
 *
 */
public enum Role
{

	READ, // Basic permission, allows the user to read the news
	SEARCH, // Basic permission, allows the user to search/query the news
	ADD_NEWS, // Allows the user to add new news
	DELETE_NEWS, // Allows the user to delete news
	EDIT_NEWS, // Allows the user to edit existing news
	IMPORT_NEWS_FROM_FILE, // Allows the user to import news from another file
	MANAGE_USER; // ADMIN level permission, allows the user to manage both its own roles and the one
					// of the other users.

}

#Advanced Programming 2015/16 Project Report#
**Title**: News feed - basic RSS feed reader

**Student**: Matteo Nardini - mnardini@unibz.it

**Student ID**: TODO


##Description##
This is my exam project for the programming course of summer 2016. This application is a basic RSS news feed reader. The user can
read, edit and write `News` and can keep them organized in `News Channel`. The user can also import a new RSS feed from a file and can
export the existing news channel to an HTML or an RTF file. Each user has a list of roles, which defines what he/she can or cannot do. The first
user that registers itself in the application obtains automatically all the roles, include the one required to modify other users' roles. All the
following user that register will only be able to read and search the news. The description of the available roles is:

* **READ**: Basic permission, allows the user to read the news;
* **SEARCH**: Basic permission, allows the user to search/query the news;
* **ADD_NEWS**: Allows the user to add new news;
* **DELETE_NEWS**: Allows the user to delete news;
* **EDIT_NEWS**: Allows the user to edit existing news;
* **IMPORT_NEWS_FROM_FILE**: Allows the user to import news from another file;
* **MANAGE_USER**: *ADMIN* level permission, allows the user to manage both its own roles and the ones of the other users.

The data is persistently stored in two XML file, one for the users and one for the news channel. These two file are stored in the **data** folder. All the user avatars are copied and stored in the **assets** folder, in order to ensure that they are always available, even if the original image selected is deleted.

##Advanced programming techniques##
This section reports the list of all the *Advanced Programming Techniques* used in the project, as requested in the requirements.

####JUnit Test Cases and Test Suite####
This feature was used all the classes located in the **test** source folder. In most of the cases, only the `@Test` annotation was used. In the following classes also the `@Before` and `@After` annotations have been used:

* `tests.ap2016.entities.NewsChannelTest`;
* `tests.ap2016.entities.NewsTest`.

JUnit tests have been used to cover all of the classes that manage the logic of the application. Also the new components created for this application (`ValidableTextField`, `ValidablePasswordField`, `AvatarImageDisplay`) have been tested.

####Exception handling####
Some special exceptions have been defined for this application. They are mostly used to describe situations in which the input given by the user is not valid. The exceptions defined for this application are:

* `ap2016.exceptions.InvalidAvatarNameException`;
* `ap2016.exceptions.InvalidLanguageStringException`;
* `ap2016.exceptions.InvalidPasswordException`;
* `ap2016.exceptions.InvalidURLException`;
* `ap2016.exceptions.InvalidUsernameException`.

Exception have been handled in the following situations:

* `ap2016.application.ApplicationConstants`, line 82-88;
* `ap2016.entities.News`, line 148-150;
* `ap2016.entities.NewsChannel`, line 132-134, 148-150;
* `ap2016.entities.User`, line 52-60, 73-79, 83-94, 124-136, 402-406, 435-443, 460-462, 476-478;
* `ap2016.gui.frames.ExportJDialog`, line 141-272;
* `ap2016.gui.frames.LoginJFrame`, line 95-105;
* `ap2016.gui.frames.MainJFrame`, line 643-648, 751-757, 792-799, 906-921;
* `ap2016.gui.frames.ManageUserJDialog`, line 172-178;
* `ap2016.gui.frames.RegisterJFrame`, line 117-124, 209-216, 284-296;
* `ap2016.gui.panels.NewsListPanel`, line 670-687;
* `ap2016.gui.io.DataProvider`, line 102-115, 125-136, 144-164;
* `ap2016.gui.utilities.ValidablePasswordField`, line 85-92;
* `ap2016.gui.utilities.ValidableTextField`, line 85-92;
* `tests.ap2016.entities.UserTests`, line 25-31, 52-58;


####XML####
All the persistent storage of data of the application is handled through XML. This advanced programming technique is used extensively
in all the classes of the `ap2016.io` package. I do not report the exact lines because almost every method of these classes deals with XML-related functions. This is a precise design choice: the classes of the `ap2016.io` are (mostly) the only one that interact directly with XML (there is an exception discussed further on). They parse the XML files and expose their content to the rest of the application as `ArrayList`s, which allow for an easier management of the data themselves.

The only exception to this rule is in the class `ap2016.gui.frames.ExportJDialog`. In this class, when the button "Export" is clicked and the selected export format is "HTML", the application exports directly the data to an HTML file which is generated in a way that uses XML-related functions. This happens at lines 133-273.

####Generics & Collections####
Generics are extensively used in the application, particularly in the following situations:

* `ap2016.gui.frames.MainJFrame`, line 63-64, 75-79;
* `ap2016.gui.utilities.ValidablePasswordField`, line 28;
* `ap2016.gui.utilities.ValidableTextField`, line 28;
* `ap2016.gui.utilities.ViewEditComponent`, whole class;

Also collections are used, particularly in the following situations:

* `ap2016.entities.NewsChannel`, line 19-22, 63-65, 94-98;
* `ap2016.entities.User`, line 188-192, 259-261, 307-321, 332-360, 372-382;
* `ap2016.gui.frames.ExportJDialog`, line 54-55, 76-84;
* `ap2016.gui.frames.LoginJFrame`, line 30, 107-108;
* `ap2016.gui.frames.MainJFrame`, line 710-807;
* `ap2016.gui.frames.ManageUserJDialog`, line 51-52, 59, 95-100;
* `ap2016.gui.panels.NewsListPanel`, line 48, 76-79;
* `ap2016.gui.utilities.ViewEditComponent`, line 73;
* `ap2016.gui.utilities.NewsChannelCheckboxListCellRenderer`, line 24, 30, 39, 59;
* `ap2016.gui.utilities.RoleCheckboxListCellRenderer`, line 38;
* `tests.ap2016.application.ApplicationUtilitiesTests`, line 52-72, 77-153;
* `tests.ap2016.entities.NewsChannelTest`, line 26-38, 67-74;
* `tests.ap2016.io.NewsChannelDataProviderTest`, line 33-94;
* `tests.ap2016.io.UserDataProviderTest`, line 53-89;

####Reflection & RTTI####
RTTI was used in the following class:

* `ap2016.gui.frames.MainJFrame`, line 696-699;

Reflection was used in the following class:

* `ap2016.gui.frames.MainJFrame`, line 753, 795, 102-1007;

####I/O & Streams####
I/O and streams were used in the following classes:

* `ap2016.entities.User`, line 74-76, 394-396, 402-410, 438-439;
* `ap2016.gui.frames.ExportJDialog`, line 262-266, 320-329;
* `ap2016.io.DataProvider`, line 105, 128, 157-160;

####Regular expression####
This advanced programming technique was used mostly for user input validation. In detail, it has been used in the following classes:

* `ap2016.application.ApplicationConstants`, line 21-41;
* `ap2016.application.ApplicationUtilities`, line 43-61;
* `ap2016.entities.User`, line 153-161, 141-151;
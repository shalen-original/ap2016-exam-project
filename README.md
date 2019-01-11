# Advanced Programming 2016 exam

## Code repository for the exam project

This repository contains the code for the 2016 advanced programming exam project.
The requirements for this project can be found in the `project-description.pdf` file.

## Package description

The project includes the following source packages:

* `ap2016.application`: Contains application-wide informations, such as useful constants and methods.

* `ap2016.entities`: Contains all the data entities used in the project.

* `ap2016.exceptions`: Contains all the specific exceptions used by the application.

* `ap2016.gui.frames`: Contains all the main windows used by the program (both JFrame and JDialog).

* `ap2016.gui.panels`: Contains all the panels dynamically added to the main frames and dialogs.

* `ap2016.gui.utilities`: Contains all the utilities used by the GUI. This package includes, among others, the *main* class, some
	JComponent extensions created for this project and some ListCellRenderers.

* `ap2016.io`: Contains all the I/O of the application.
  
 The `tests` folder contains all the tests created for this application. The structure of the `tests` folder mirrors the one of the
 `src` folder.

## How to setup the enviroment

The following steps are required:

* Clone this repository;
* Import the folder as an Eclipse project;
* You're good to go.

## Copyright

All the resources on this repository are owned by Matteo Nardini. All rights reserved, (C) 2016.
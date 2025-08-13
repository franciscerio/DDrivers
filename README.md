<img src="https://github.com/franciscerio/ota-android/blob/master/images/Screen%20Shot%202024-08-21%20at%208.33.27%20PM.png" width="450"/>

# Sample app requirements
- [x] Shows the list of delivery stops.
- [x] Lets the driver mark deliveries as Completed or Skipped.
- [ ] Allows the driver to reorder the list manually.
- [ ] Has an “Optimize Route” button that reorders the stops automatically (you
  decide the logic)
- [x] Works even if the app is closed and reopened (save progress).|

Note:
If you want to reset the data, just tap the topbar textview named "Home".


# Requirements
- Minimum SDK 26

# UI toolkit
- Jetpack Compose
- Material3

# Notable Libraries used
- Shared preferences
- Dagger Hilt

# Architecture (MVI and multi-module)
- The MVI (Model-View-Intent) architecture, a popular approach in Android development, emphasizes clean code and a clear separation of concerns by dividing the application into three main components: Model, View, and Intent.

# Future improvements
- Integrate Room 

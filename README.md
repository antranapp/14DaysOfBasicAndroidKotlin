# 30 days of Basic Android (Kotlin)

## Basic:

### Day 0: Hello World

- Create a new Android project with an empty activity
- Add a new resource file with the name `id.xml` to store the id of views
- Connect the `Label` to `MainActivity`
    - Set `id`
    - Add `findViewById`
- "Say Hello" `Button`:
    - Add a `Button`
    - Add `Constraints` for the `Button`
    - Change the `Text` attribute for the `Button` to "Say Hello"
    - Connect to `MainActivity`
        - Set `id`
        - Add `findViewById`
    - Print `Logcat` - Print `Toast`
- "Name" `TextView`: 
    - Add a `TextView`
        - Change `inputType` to `textShortMessage`
        - Change `focusable` to `true`
        - Change `focusableInTouchMode` to `true`
        - Change `clickable` to `true`
        - Change `selectAllOnFocus` to `true`
        - Set `id`
        - Add `findViewById`
    - Add `Constraints` for the `TextView` -> Connect to `MainActivity`
- Print `Hello + username` in the label when click the `Button`

### Day 1: Layout Explorer
    - LinearLayout (Vertical)
    - LinearLayout (Horizontal)
    - RelativeLayout
    - ConstraintLayout
    - FrameLayout
    - TableLayout
    ---------
    - layout_height, layout_width: wrap_content, match_parent
    - padding, margin
    - gravity
    - layout_gravity

### Day 2: Widget Explorer
    - TextView
    - Button
    - Switch
    - Checkbox
    - RadioButton + RadioGroup
    - ProgressBar
    - Spinner

### Day 3: CardView + ScrollView

### Day 4: Application Lifecycle

### Day 5: ListView

### Day 6: RecyclerView + RecyclerAdapter

### Day 7: Data persistence

### Day 8: Android Database

### Day 9: Networking

### Day 10: Localizaion

### Day 11: Fragment
    - Conditionally showing the LoginFragment or the ProfileFragment in MainActivity
    - Navigation from the ProfileFragment to LogoutFragment which has a logout button.
    - Logged-In state is saved in SharedPreferences after logging in
    - After logging out, users are redirected to the LoginFragment again.

### Day 12: Full App: Pixabay Photo Gallery
    - Infinit scrolling with RecyclerView & RecyclerAdapter
    - Using Volley to make network request. 

## Advanced:

- Advanced UI: Material Design - Theming
- Advanced UI: NavigationView
- Advanced UI: Paging and Swiping
- Advanced UI: Navigation Drawer and Fragment
- Advanced UI: The app bar
- Rotation handling 
- Animation
- Drawing Graphics
- Sound effects
- Architecture: Model-View-Controller (MVC)
- Architecture: Model-View-ViewModel (MVVM)
- Architecture: Model-View-Intent (MVI)
- Persisting UI State
- Debugging Android Apps
- Intents
- Accessibility

## Jetpack:

- Architecture Component:
- Navigation Component:
- Paging Component: https://www.raywenderlich.com/6948-paging-library-for-android-with-kotlin-creating-infinite-lists


Application with event tracking on AOP (AspectJ), annotatations and reflection

How use?

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher{

    public static final String TAG = MainActivity.class.getSimpleName();

    @LoggerView("first button")
    public Button button;

    public Artist artist = new Artist("123", "qwe");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         /*   init views   */
        ViewEventsInjector.init();
        ViewEventsInjector.inject(this);
    }

    @Override
    @AttachState({"artist"})
    @ViewEvent(ViewEventsTracker.AFTER_TEXT_CHANGED)
    public void afterTextChanged(Editable s) {
        Log.d(TAG, "afterTextChanged");
    }
}
```

1.  Annotate view with @LoggerView("view_name")
2.  Annotate event method with  @ViewEvent(event_code)
3.  Attache object to event with  @AttachState({"filed_name"})

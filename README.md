BookingDemo

My approach to this was to
1: create models for the data we were expecting back
2: creating the UI components for those models
3: mocking some models to make sure the UI looked the way I wanted it to
4: swap out the mocked models with real data from the network
5: add another ui element to house a "Selected" model (which deviates from the web design)

When I first looked at the mock up for web I decided that a "book" button on each list element might cause mobile users to accidentally tap while scrolling. Instead I decided that it might be better to have the user first select a room and then confirm booking it with a button at the bottom.

Some other minor improvements I took the liberty to add were making rooms with 0 spots available show red text with a greyscale image and disable the "book button" when those rooms are under selection. Also, you won't see it with the current test data but I'm taking advantage of plural strings which will show "1 spot" vs "2 spots".

Libraries used:
- Picasso for image loading
- OkHttp for network calls
- An OkHttp helper lib that lets me suspend and await our network calls. I think this looks nicer than chains of callbacks but it's just personal preference 
- Gson for parsing JSON
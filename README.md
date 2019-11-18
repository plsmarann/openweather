# openweatehr
Implementation
I used an architecture based on the MVP pattern and followed some Clean Architecture concepts. The application is divided in three main packages: Model, Data and View.

Model:  models.
Data: Responsible for managing the flow of data used in the application.
View (View + Presenter): Responsible for the presentation layer.
Libraries
RxJava
Retrofit + OkHttp
ButterKnife
ORMLite
Gson
Dagger


Uses the Open Weather API (https://openweathermap.org/api) to get current
weather data.

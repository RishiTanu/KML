package com.example.kmldatastructure

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideCookieJar(): CookieJar = SimpleCookieJar()

    @Singleton
    @Provides
    fun provideOkHttpClient(cookieJar: CookieJar, tokenInterceptor: TokenInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .addInterceptor(tokenInterceptor) // Add the interceptor to OkHttpClient
            .build()

    // Provide Retrofit and ApiService as previously shown
}

## Tpaga android-sdk

Tpaga SDK allows your application connect with the Tpaga API to create new tokens which represents a credit cards. Have two options to generate credit card tokens. The first include a fragment where you can to write the credit card data or scan it. 
The other option is to make the request to the Tpaga API. Both return a credit card token. You can show a implementation example here [Example Tpaga SDK](https://github.com/AdelaTpaga/android-sdk/tree/master/SampleTpagaSdk/app)  

### Latest release

The most recent release is tpagasdk 1.0.5, released December 1, 2016 

### Get Started

1. Add depencency: 

-Using Maven add the following

```
<dependency>
  <groupId>co.tpaga</groupId>
  <artifactId>tpagasdk</artifactId>
  <version>1.0.5</version>
  <type>pom</type>
</dependency>
```

-Using Gradle

```
dependencies{
  compile 'co.tpaga:tpagasdk:1.0.4'
}
```

-Using Ivy

```
<dependency org='co.tpaga' name='tpagasdk' rev='1.0.5'>
  <artifact name='tpagasdk' ext='pom' />
</dependency>
```


1. Download and import tpagasdk module into project


2. Add ‘compile project(":tpagasdk")’ in your app build.gradle


```
dependencies {
 .
 .
    compile project(":tpagasdk")
 .
 .
 }
```
3. In the Activity or Application file initialize tpagasdk. Set your context, public key and select environment.


```
TpagaAPI apiTpaga = new TpagaAPI(this, getResources().getString(R.string.tpaga_public_api_key), TpagaAPI.SANDBOX);
```


Use your project public_api_key to initialize  


You found the key in your dashboard in https://tpaga.co/


Add Credit Card


there are two options to add credit cards


Add AddCreditCardFragment fragment in your activity
```
FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
AddCreditCardFragment mAddCreditCardFragment = new AddCreditCardFragment();
ft.add(R.id.content_default, fragment, fragmentTag);
ft.addToBackStack(fragmentTag);
ft.commitAllowingStateLoss();
```
Your activity must implements AddCreditCardView and override saveCreditCardSuccess method


This method is execute if fields in fragment are correct 
```
@Override
    public void saveCreditCardSuccess(Account account) {
      //Your code
    }

```


@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAddCreditCardFragment.onActivityResult(requestCode, resultCode, data);
    }




Customize AddCreditCardFragment


Override the styles in from project


<style name="button_red" parent="Theme.AppCompat.Light"></style>
<style name="title_style"></style>
<style name="default_edit_text_style" parent="@style/Base.Widget.AppCompat.EditText"></style>



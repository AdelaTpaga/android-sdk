## Tpaga android-sdk
--description sdk
### Latest release
--coming soon :P 
## Get Started

### 

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



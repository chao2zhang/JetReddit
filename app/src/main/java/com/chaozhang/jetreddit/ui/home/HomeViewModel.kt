package com.chaozhang.jetreddit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

  private val _text = MutableLiveData("This is home Fragment")
  val text: LiveData<String> = _text
}
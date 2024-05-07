package com.example.dartsapplication.screens.bobsts.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dartsapplication.screens.bobsts.BobsTSRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BobsViewModel @Inject constructor(
    private val repository: BobsTSRepository,
) : ViewModel() {}
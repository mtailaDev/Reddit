package com.example.matthewtaila.redditstockx.di

import com.example.matthewtaila.redditstockx.common.network.NetworkServices
import org.koin.dsl.module.module

val networkServiceModule = module {
    single<NetworkServices> { NetworkServices() }
}
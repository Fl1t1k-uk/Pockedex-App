package org.train.projecto.di

import org.koin.core.module.Module

    expect val platformModule: Module //Він дає різні залежності для різних платформ, бо в нас на різних платформах різні builders.
    //Це им для того, щоб мати спільні builders, а потім використати їх в Datamodule




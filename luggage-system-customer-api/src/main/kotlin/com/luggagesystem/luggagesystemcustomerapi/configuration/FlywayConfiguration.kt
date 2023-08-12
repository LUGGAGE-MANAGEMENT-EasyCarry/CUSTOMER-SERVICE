package com.luggagesystem.luggagesystemcustomerapi.configuration

import com.luggagesystem.luggagesystemcustomerapi.configuration.properties.DatabaseProperties
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FlywayConfiguration(private val databaseProperties: DatabaseProperties) {

    @Bean(initMethod = "migrate")
    fun flyway():Flyway{
        return  Flyway(
            with(databaseProperties){
                Flyway.configure().baselineOnMigrate(true)
                    .dataSource(getDatabaseUrl(host,port,name),username,password)

            }
        )
    }

    fun getDatabaseUrl(host:String,port:String,name:String):String{
        return "jdbc:postgresql://$host:$port/$name"
    }
    //With these configuration ConnectionFactory will establish connection to the Postgresql DB.
    //Flyway migration will also check its migration scripts anda scheme version. Remember, we did not create any migration script yet. We will talk about this in the next steps.

}
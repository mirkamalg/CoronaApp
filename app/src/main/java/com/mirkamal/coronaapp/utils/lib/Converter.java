package com.mirkamal.coronaapp.utils.lib;

import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.model.pojo.CountryPOJO;

public class Converter {

    public static CountryPOJO convertCountryToCountryPOJO(Country country) {
        return new CountryPOJO(country.getName(), country.getTotalCases(), country.getNewCases()
                , country.getTotalDeaths(), country.getNewDeaths(), country.getTotalRecovered(), country.getNewRecovered());
    }

    public static Country convertCountryPOJOToCountry(CountryPOJO countryPOJO) {
        return new Country(countryPOJO.getName(), countryPOJO.getTotalCases(), countryPOJO.getNewCases()
        , countryPOJO.getTotalDeaths(), countryPOJO.getNewDeaths(), countryPOJO.getTotalRecovered(), countryPOJO.getNewRecovered());
    }

}

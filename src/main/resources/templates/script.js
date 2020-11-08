(function () {
    const MS_PER_MINUTE = 60000;

    let setField = (fieldName, value) => {
        let field = document.getElementById(fieldName);
        if (field.type === "checkbox") {
            field.checked = value;
        } else {
            field.value = value;
        }
    };

    function getDate(minutesDepart) {
        var now = new Date();
        now = new Date(now - Number(minutesDepart) * MS_PER_MINUTE);

        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);

        let date = now.getFullYear() + "-" + (month) + "-" + (day);

        var hours = ("0" + now.getHours()).slice(-2);
        var minutes = ("0" + now.getMinutes()).slice(-2);
        let hour = hours + ":" + minutes;

        return {date, hour};
    };
    setField('field-firstname', [[${prenom}]]);
    setField('field-lastname', [[${nom}]]);
    setField('field-birthday', [[${dateNaissance}]]);
    setField('field-placeofbirth', [[${lieuNaissance}]]);
    setField('field-address', [[${adresse}]]);
    setField('field-city', [[${ville}]]);
    setField('field-zipcode', [[${codePostal}]]);
    const {date, hour} = getDate([(${minutesDepart})]);
    setField('field-datesortie', date);
    setField('field-heuresortie', hour);
    let motifs = [[${motifs}]].split(",");
    for (motif of motifs) {
        switch (motif) {
            case "ACHATS":
                setField('checkbox-achats', true);
                break;
            case "TRAVAIL":
                setField('checkbox-travail', true);
                break;
            case "SANTE":
                setField('checkbox-sante', true);
                break;
            case "MOTIF_FAMILIAL":
                setField('checkbox-famille', true);
                break;
            case "HANDICAP":
                setField('checkbox-handicap', true);
                break;
            case "SPORT_ANIMAUX":
                setField('checkbox-sport_animaux', true);
                break;
            case "CONVOCATION":
                setField('checkbox-convocation', true);
                break;
            case "MISSIONS":
                setField('checkbox-missions', true);
                break;
            case "ENFANTS":
                setField('checkbox-enfants', true);
                break;
        }
    }
    document.getElementById('generate-btn').click();
})();

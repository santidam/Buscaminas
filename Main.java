//email
        do {
String email = InputData.inputStrLine("Escribe tu correo electrónico: ");
        } while (!Validations.validarCorreoElectronico(email));
        }
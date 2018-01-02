package exceptions

class TokenKeyNotPresent extends Exception {

    private static final String COMMON_MESSAGE =
            '''TOKEN KEY was not found in the command line argument. Please make sure that you passing TOKEN_KEY as a -DTOKEN_KEY='<<TOKE_KEY_VALUE>>' as a command line argument" in application.yml file'''

    TokenKeyNotPresent(){
        super(COMMON_MESSAGE)
    }

    TokenKeyNotPresent(String message){
        super(message)
    }

    @Override
    String toString(){
        getMessage()
    }

}

package com.megvii.api;

public class FaceIDConst
{
    // region Base
    public static final String API_PARAM_API_KEY = "api_key";
    public static final String API_PARAM_API_SECRET = "api_secret";
    public static final String API_PARAM_REQUEST_ID = "request_id";
    public static final String API_PARAM_TIME_USED = "time_used";
    // endregion

    // region Detect
    public static final String API_PARAM_FACES = "faces";
    public static final String API_PARAM_QUALITY_THRESHOLD = "quality_threshold";
    public static final String API_PARAM_LEFT = "left";
    public static final String API_PARAM_TOP = "top";
    public static final String API_PARAM_WIDTH = "width";
    public static final String API_PARAM_HEIGHT = "height";
    public static final String API_PARAM_TOKEN = "token";
    public static final String API_PARAM_ORIENTATION = "orientation";
    // endregion

    // region IDCard OCR V1
    public static final String API_PARAM_SIDE_FRONT = "front";
    public static final String API_PARAM_SIDE_BACK = "back";
    public static final String API_PARAM_BIRTHDAY_YEAR = "year";
    public static final String API_PARAM_BIRTHDAY_MONTH = "month";
    public static final String API_PARAM_BIRTHDAY_DAY = "day";
    public static final String API_PARAM_ID_CARD_NUMBER = "id_card_number";
    public static final String API_PARAM_RACE = "race";
    public static final String API_PARAM_VALID_DATE = "valid_date";
    public static final String API_PARAM_HEAD_RECT = "head_rect";
    public static final String API_PARAM_LEGALITY_ID_PHOTO_V1 = "ID Photo";
    public static final String API_PARAM_LEGALITY_TEMPORARY_ID_PHOTO_V1 = "Temporary ID Photo";
    // endregion

    // region IDCard OCR V2
    public static final String API_PARAM_PORTRAIT = "portrait";
    public static final String API_PARAM_RETURN_PORTRAIT = "return_portrait";
    public static final String API_PARAM_LOGIC = "logic";
    public static final String API_PARAM_COMPLETENESS = "completeness";
    public static final String API_PARAM_CARD_RECT = "card_rect";
    public static final String API_PARAM_VALID_DATE_START = "valid_date_start";
    public static final String API_PARAM_VALID_DATE_END = "valid_date_end";
    public static final String API_PARAM_LEGALITY_ID_PHOTO_THRESHOLD = "ID_Photo_Threshold";
    public static final String API_PARAM_LEGALITY_ID_PHOTO_V2 = "ID_Photo";
    public static final String API_PARAM_LEGALITY_TEMPORARY_ID_PHOTO_V2 = "Temporary_ID_Photo";
    public static final String API_PARAM_BIRTH_YEAR = "birth_year";
    public static final String API_PARAM_BIRTH_MONTH = "birth_month";
    public static final String API_PARAM_BIRTH_DAY = "birth_day";
    public static final String API_PARAM_NATIONALITY = "nationality";
    // endregion

    // region BankCard
    public static final String API_PARAM_BANK = "bank";
    public static final String API_PARAM_NUMBER = "number";
    public static final String API_PARAM_ORGANIZATION = "organization";
    public static final String API_PARAM_LEFT_BOTTOM = "left_bottom";
    public static final String API_PARAM_RIGHT_TOP = "right_top";
    public static final String API_PARAM_RIGHT_BOTTOM = "right_bottom";
    public static final String API_PARAM_LEFT_TOP = "left_top";
    public static final String API_PARAM_POSITION = "position";
    // endregion

    // region Verify
    public static final String API_PARAM_COMPARISON_TYPE = "comparison_type";
    public static final String API_PARAM_FACE_IMAGE_TYPE = "face_image_type";
    public static final String API_PARAM_IDCARD_NAME = "idcard_name";
    public static final String API_PARAM_IMAGE_REF = "image_ref";
    public static final String API_PARAM_IMAGE_REF1 = "image_ref1";
    public static final String API_PARAM_IMAGE_REF2 = "image_ref2";
    public static final String API_PARAM_IMAGE_REF3 = "image_ref3";
    public static final String API_PARAM_UUID = "uuid";
    public static final String API_PARAM_DELTA = "delta";
    public static final String API_PARAM_IMAGE_BEST = "image_best";
    public static final String API_PARAM_IMAGE_ENV = "image_env";
    public static final String API_PARAM_IMAGE_ACTION = "image_action";
    public static final String API_PARAM_IMAGE_ACTION1 = API_PARAM_IMAGE_ACTION + "1";
    public static final String API_PARAM_IMAGE_ACTION2 = API_PARAM_IMAGE_ACTION + "2";
    public static final String API_PARAM_IMAGE_ACTION3 = API_PARAM_IMAGE_ACTION + "3";
    public static final String API_PARAM_IMAGE_ACTION4 = API_PARAM_IMAGE_ACTION + "4";
    public static final String API_PARAM_IMAGE_ACTION5 = API_PARAM_IMAGE_ACTION + "5";
    public static final String API_PARAM_CHECK_DELTA = "check_delta";
    public static final String API_PARAM_FACE_TOKEN = "face_token";
    public static final String API_PARAM_FAIL_WHEN_MULTIPLE_FACES = "fail_when_multiple_faces";
    public static final String API_PARAM_FACE_QUALITY_THRESHOLD = "face_quality_threshold";
    public static final String API_PARAM_RETURN_FACES = "return_faces";
    public static final String API_PARAM_MEGLIVE_FLASH_RESULT = "meglive_flash_result";
    public static final String API_PARAM_MULTI_ORIENTED_DETECTION = "multi_oriented_detection";
    // endregion

    // region Common
    public static final String API_PARAM_NAME = "name";
    public static final String API_PARAM_SIDE = "side";
    public static final String API_PARAM_IMAGE = "image";
    public static final String API_PARAM_QUALITY = "quality";
    public static final String API_PARAM_RECT = "rect";
    public static final String API_PARAM_RECT_RT = "rt";
    public static final String API_PARAM_RECT_LT = "lt";
    public static final String API_PARAM_RECT_RB = "rb";
    public static final String API_PARAM_RECT_LB = "lb";
    public static final String API_PARAM_RECT_X = "x";
    public static final String API_PARAM_RECT_Y = "y";
    public static final String API_PARAM_ISSUED_BY = "issued_by";
    public static final String API_PARAM_LEGALITY = "legality";
    public static final String API_PARAM_LEGALITY_EDITED = "Edited";
    public static final String API_PARAM_LEGALITY_PHOTOCOPY = "Photocopy";
    public static final String API_PARAM_LEGALITY_SCREEN = "Screen";
    public static final String API_PARAM_ADDRESS = "address";
    public static final String API_PARAM_BIRTHDAY = "birthday";
    public static final String API_PARAM_GENDER = "gender";
    public static final String API_PARAM_IDCARD_NUMBER = "idcard_number";
    public static final String API_PARAM_RESULT = "result";
    // endregion
}

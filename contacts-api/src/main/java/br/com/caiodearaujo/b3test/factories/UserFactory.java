package br.com.caiodearaujo.b3test.factories;

import br.com.caiodearaujo.b3test.dto.UserDTO;
import br.com.caiodearaujo.b3test.entities.User;
import br.com.caiodearaujo.b3test.exceptions.InvalidUserDataException;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserFactory {

    private UserDTO dto;
    private static UserFactory singleInstance;
    private static final String EMAIL_REGEX_VALIDATION = "^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))(@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$)|(@\\[?((25[0-5]\\.|2[0-4][0-9]\\.|1[0-9]{2}\\.|[0-9]{1,2}\\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\]?$)";

    public static UserFactory getInstance() {
        if (singleInstance == null) {
            singleInstance = new UserFactory();
        }
        return singleInstance;
    }

    /**
     * @param dto
     * @return
     * @throws InvalidUserDataException
     * @throws NullPointerException
     */
    public User toEntity(UserDTO dto) throws InvalidUserDataException, NullPointerException {
        this.dto = getValidDto(dto);
        validEmail();
        validCompanyId();
        return User.builder()
                .companyId(dto.getCompanyId())
                .email(dto.getEmail())
                .birthdate(dto.getBirthdate())
                .build();
    }

    /**
     * @param dto
     * @return
     */
    private UserDTO getValidDto(UserDTO dto) {
        return Objects.requireNonNull(dto);
    }

    /**
     * @throws InvalidUserDataException
     */
    private void validCompanyId() throws InvalidUserDataException {
        if (!Arrays.asList(User.VALID_COMPANY_IDS).contains(dto.getCompanyId())) {
            throw new InvalidUserDataException(
                    String.format("User with email %s contains a companyId %d out of range accepted",
                            dto.getEmail(), dto.getCompanyId()), dto);
        }
    }

    /**
     * @throws InvalidUserDataException
     */
    private void validEmail() throws InvalidUserDataException {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_VALIDATION);
        if (!pattern.matcher(dto.getEmail()).matches()) {
            throw new InvalidUserDataException(
                    String.format("User with email = %s contains that is in invalid email format.", dto.getEmail()), dto);
        }
    }

}

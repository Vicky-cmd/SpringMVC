package com.infotrends.in.models;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class UserModel {

    private int userId;

    @NonNull
    private String username;

    private String name;

}


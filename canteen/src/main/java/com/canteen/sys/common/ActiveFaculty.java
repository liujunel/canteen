package com.canteen.sys.common;

import com.canteen.sys.domain.Faculty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author:junle
 * @create:2020/2/18-23:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveFaculty {

    private Faculty faculty;

    private String roleName;

    private List<String> permissions;
}

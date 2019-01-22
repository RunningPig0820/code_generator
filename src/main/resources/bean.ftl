package ${packageName};

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName WanDaRequest
 * @Description ${description}
 * @Author ${author}
 * @Date ${date}
 * @Version 1.0
 **/
@ApiModel
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ${className} {

<#list attrs as attr>
    <#if attr.isNotNull == "true">
    @NotNull
    </#if>
    @ApiModelProperty("${attr.notes}")
    private ${attr.type} ${attr.name};

</#list>
}
package com.moxie.taosha.code_generator_kotlin.bean

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
class Attribute{

        constructor()

        constructor(isNotNull: String?, notes: String?, type: String?, name: String?) {
                this.isNotNull = isNotNull
                this.notes = notes
                this.type = type
                this.name = name
        }

        /**
        * 是否不为空
        */
        internal var isNotNull: String? = null
        /**
        * bean 注释
        */
        internal var notes: String? = null
        /**
        * 字段类型
        */
        internal var type: String? = null
        /**
        * 字段名称
        */
        internal var name: String? = null
}
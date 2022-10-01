import { defineStore } from 'pinia'
import { SiteConfig } from '/@/stores/interface'

export const useSiteConfig = defineStore('siteConfig', {
    state: (): SiteConfig => {
        return {
            site_name: '',
            record_number: '',
            version: '',
            cdn_url: '',
            api_url: '',
            upload: {
                mode: 'local',
                maxsize: 0,
                mimetype: '',
                savename: '',
            },
        }
    },
    actions: {
        dataFill(state: SiteConfig) {
            this.$state = state
        },
    },
})

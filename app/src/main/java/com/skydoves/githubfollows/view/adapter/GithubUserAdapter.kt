package com.skydoves.githubfollows.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.skydoves.githubfollows.R
import com.skydoves.githubfollows.models.Follower
import com.skydoves.githubfollows.models.GithubUser
import com.skydoves.githubfollows.models.Resource
import com.skydoves.githubfollows.view.viewholder.GithubUserHeaderViewHolder
import com.skydoves.githubfollows.view.viewholder.GithubUserViewHolder

/**
 * Developed by skydoves on 2018-01-21.
 * Copyright (c) 2018 skydoves rights reserved.
 */

@Suppress("PrivatePropertyName", "MemberVisibilityCanBePrivate")
class GithubUserAdapter(
  private val delegate_header: GithubUserHeaderViewHolder.Delegate,
  private val delegate: GithubUserViewHolder.Delegate
) : BaseAdapter() {

    private val section_header = 0
    private val section_follower = 1

    init {
        addSection(ArrayList<GithubUser>())
        addSection(ArrayList<Follower>())
    }

    fun updateHeader(resource: Resource<GithubUser>) {
        resource.data?.let {
            sections()[section_header].clear()
            sections()[section_header].add(it)
            notifyDataSetChanged()
        }
    }

    fun addFollowList(followers: List<Follower>) {
        sections()[section_follower].addAll(followers)
        notifyDataSetChanged()
    }

    fun clearAll() {
        sections()[section_header].clear()
        sections()[section_follower].clear()
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow): Int {
        if (sectionRow.section == section_header) return R.layout.layout_detail_header
        return R.layout.item_github_user
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return when (layout) {
            R.layout.layout_detail_header -> GithubUserHeaderViewHolder(view, delegate_header)
            else -> GithubUserViewHolder(view, delegate)
        }
    }
}
